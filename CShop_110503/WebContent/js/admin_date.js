
    var selectDate;
    var selectDateStr;
    var selectMonth;
    var selectMonthStr;
    var selectStart;
    var selectStartStr;
    var selectEnd;
    var selectEndStr;
    //var date;
    $(function(){
        var choice_button = $(".admin-button-choice").eq(0);
        choice_button.css("background-color","#5F755A").css("color","#ffffff").css("font-weight","bold");
        $(".admin-table").eq(0).css("display","table");
        $(".admin-table").eq(1).css("display","none");
        $(".admin-table").eq(2).css("display","none");
        $(".date-form").eq(0).css("display","block");
        $(".date-form").eq(1).css("display","none");
        $(".date-form").eq(2).css("display","none");
        
/*
        selectDate = getToday();
        function getToday() {
            var today = new Date();
            var todayStr = toStringDate(today);
            $("#daily").val(todayStr);
            selectDateStr = todayStr;
            selectDate = new Date($("#daily").val());
            return today;
        }
        selectMonth = getPrevMonth();
        function getPrevMonth() {
            var today = new Date();
            today.setMonth(today.getMonth()-1);
            var todayStr = toStringMonth(today);
            $("#month").val(todayStr);
            selectMonthStr = todayStr;
            selectMonth = new Date($("#month").val());
            return today;
        }
        selectStart = getStart();
        function getStart() {
            var today = new Date();
            var todayStr = toStringDate(today);
            $("#periodStart").val(todayStr);
            selectStartStr = todayStr;
            selectStart = new Date($("#periodStart").val());
            return today;
        }
        selectStart = getEnd();
        function getEnd() {
            var today = new Date();
            var todayStr = toStringDate(today);
            $("#periodEnd").val(todayStr);
            selectEndStr = todayStr;
            selectEnd = new Date($("#periodEnd").val());
            return today;
        }
*/

    });

    function admin_button_choice(choice) {
        for(var i=0; i<$(".admin-button-choice").length; i++){
            $(".admin-button-choice").eq(i).css("background-color","#e0e0e0").css("color","#000000").css("font-weight","normal");
            $(".admin-table").eq(i).css("display","none");
            $(".date-form").eq(i).css("display","none");
        }
        var choice_button = $(".admin-button-choice").eq(choice-1);
        choice_button.css("background-color","#5F755A").css("color","#ffffff").css("font-weight","bold");
        $(".admin-table").eq(choice-1).css("display","table");
        $(".date-form").eq(choice-1).css("display","block");

    }
    
    function toStringDate(date) {
        var date = new Date(date);
        return date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);
    }
    function toStringMonth(date) {
        var date = new Date(date);
        return date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2);
    }
    function nextDaily() {
        selectDate = new Date($("#daily").val());
        selectDateStr = toStringDate(selectDate);
        if(selectDateStr == toStringDate(new Date())){
            return false;
        }
        var newDate = selectDate.setDate(selectDate.getDate()+1);
        var newDateStr = toStringDate(newDate);
        $("#daily").val(newDateStr);
        var nDate = new Date(newDate);
        selectDate = nDate;
        selectDateStr = newDateStr;
    }
    function prevDaily() {
        selectDate = new Date($("#daily").val());
        selectDateStr = toStringDate(selectDate);
        if(selectDateStr == "2000-01-01"){
            return false;
        }
        var newDate = selectDate.setDate(selectDate.getDate()-1);
        var newDateStr = toStringDate(newDate);
        $("#daily").val(newDateStr);
        var nDate = new Date(newDate);
        selectDate = nDate;
        selectDateStr = newDateStr;
    }
    function nextMonth() {
        selectMonth = new Date($("#month").val());
        selectMonthStr = toStringMonth(selectMonth);
        var today = new Date();
        var todayMonth = today.setMonth(today.getMonth()-1)
        if(selectMonthStr == toStringMonth(todayMonth)){
            return false;
        }
        var newDate = selectMonth.setMonth(selectMonth.getMonth()+1);
        var newDateStr = toStringMonth(newDate);
        $("#month").val(newDateStr);
        var nDate = new Date(newDate);
        selectMonth = nDate;
        selectMonthStr = newDateStr;
    }
    function prevMonth() {
        selectMonth = new Date($("#month").val());
        selectMonthStr = toStringMonth(selectMonth);
        if(selectMonthStr == "2000-01"){
            return false;
        }
        var newDate = selectMonth.setMonth(selectMonth.getMonth()-1);
        var newDateStr = toStringMonth(newDate);
        $("#month").val(newDateStr);
        var nDate = new Date(newDate);
        selectMonth = nDate;
        selectMonthStr = newDateStr;
    }
    function changeStart() {
        selectDate = new Date($("#periodStart").val());
        selectDateStr = toStringDate(selectDate);
        var newDate = selectDate;
        var newDateStr = toStringDate(newDate);
        $("#periodStart").val(newDateStr);
        var nDate = new Date(newDate);
        selectStart = nDate;
        selectStartStr = newDateStr;
    }
    function changeEnd() {
        selectDate = new Date($("#periodEnd").val())
        selectDateStr = toStringDate(selectDate);
        var newDate = selectDate;
        var newDateStr = toStringDate(newDate);
        $("#periodEnd").val(newDateStr);
        var nDate = new Date(newDate);
        selectEnd = nDate;
        selectEndStr = newDateStr;
    }

    // 키보드
    // 영어와 숫자 입력 방지
    $("#daily").keydown(function (e) {
        return false;
    });
    $("#week").keydown(function (e) {
        return false;
    });
    $("#month").keydown(function (e) {
        return false;
    });
    // 한글 입력 방지
    function removeChar(event) {
        event = event || window.event;
        var keyID = (event.which) ? event.which : event.keyCode;
        if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
            return;
        else
            event.target.value = event.target.value.replace(/[^0-9]/g, "");
    }