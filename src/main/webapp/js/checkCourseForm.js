function checkCourseForm() {
    let checkResult = true;

    let scoreRegex = /^[\d.]+$/; 
    //let scoreRegex = /[0-9]+(.[0-9])/; 
    if (!scoreRegex.test($("#score").val())) {
        alert("\"評分\"只能輸入有效數字(0.0 ~ 9.9)");
        checkResult = false;
        return checkResult;
    }
    
    let score = $("#score").val();
    if (score >=10) {
		alert("\"評分\"只能輸入有效數字(0.0 ~ 9.9)，需小於10");
        checkResult = false;
    }
    
     let priceRegex = /^\d+$/; 
    if (!priceRegex.test($("#price").val())) {
        alert("\"價格\"只能輸入有效數字");
        checkResult = false;
        return checkResult;
    }
    
    let postDate = new Date($("#date").val());
    let nowDate = new Date();
    if (postDate < nowDate) {
		alert("\"上架日期\"不可在今日之前");
        checkResult = false;
    }
	return checkResult;
 }   

$('#correctInput').click(function () {
    $('#courseName').val('面試必勝10招')
    $('#courseCategory').val('求職技巧')
    $('#courseIntroduction').val('畢業季將近，即將踏入社會的準畢業生們開始尋找自己未來的出路，積極查找各種工作資訊，許多公司也紛紛開出職缺，想趁著畢業求職潮廣招人才。面對各種夢幻職缺，你知道企業面試官們最在意哪些地方嗎？')
    $('#lecturer').val('王大陸')
    $('#date').val('2022-06-01')
    $('#coursePic').val('url')
    $('#courseVedio').val('url')
    $('#score').val('9.9')
    $('#price').val('1999')
})
