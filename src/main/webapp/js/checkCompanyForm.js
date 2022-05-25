function checkCompanyForm() {
    let checkResult = true;

   let CompIdRegex = /^\d+$/; 
    if (!CompIdRegex.test($("#compid").val())) {
        alert("請輸入正確的公司統編");
        checkResult = false;
        return checkResult;
    }

    let EmpNumRegex = /^\d+$/; 
    if (!EmpNumRegex.test($("#empnumber").val())) {
        alert("員工人數只能輸入阿拉伯整數");
        checkResult = false;
        return checkResult;
    }

	return checkResult;

 }   


$('#wrongInput').click(function () {
    $('#compid').val('A7654321')
    $('#compwd').val('abc123zzz')
    $('#corpname').val('咻咻股份有限公司')
    $('#owner').val('林禹咻')
    $('#industry').val('服務業')
    $('#contact').val('唐洋基')
    $('#comptele').val('02-87654321')
    $('#fax').val('07-1325462')
    $('#compaddress').val('新北市土城工業區26號')
    $('#empnumber').val('300人')
    $('#website').val('www.giigle.com')
    $('#capital').val('1200萬')   
    
})

$('#correctInput').click(function () {
    $('#compid').val('87654321')
    $('#compwd').val('abc123zzz')
    $('#corpname').val('咻咻股份有限公司')
    $('#owner').val('林禹咻')
    $('#industry').val('服務業')
    $('#contact').val('唐洋基')
    $('#comptele').val('02-87654321')
    $('#fax').val('07-1325462')
    $('#compaddress').val('新北市土城工業區26號')
    $('#empnumber').val('300')
    $('#website').val('www.giigle.com')
    $('#capital').val('1200萬')
})
