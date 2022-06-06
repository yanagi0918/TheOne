function checkCompanyForm() {
	let checkResult = true;
	let checkJobForm = true;
	
	let compIdRegex = /^\d{8}$/;
	if (!compIdRegex.test($("#compid").val())) {
		Swal.fire('Warning!',
			'統編為8個數字!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	if($("#compwd").val().length<6){
	Swal.fire('Warning!',
			'密碼少於6位數!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}
	
	
	let comptele = /^[0-9]{10}$/g;
	if(!comptele.test($("#comptele").val())){
		Swal.fire('Warning!',
			'電話格式錯誤!',
			'warning');
		checkJobForm = false;
		return checkJobForm;
	}	
	
	let EmpNumRegex = /^\d+$/;
	if (!EmpNumRegex.test($("#empnumber").val())) {
		Swal.fire('Warning!',
			'員工人數只能輸入阿拉伯整數!',
			'warning');
		checkResult = false;
		return checkResult;
	}

	return checkResult;

}


$('#wrongInput').click(function() {
	$('#compid').val('A7654321')
	$('#compwd').val('abc')
	$('#corpname').val('麥噹勞')
	$('#owner').val('麥先生')
	$('#industry').val('服務業')
	$('#contact').val('麥小姐')
	$('#comptele').val('0939-39-3939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300人')
	$('#website').val('www.giigle.com')
	$('#capital').val('1200萬')

})

$('#correctInput').click(function() {
	$('#compid').val('87654321')
	$('#compwd').val('abc123zzz')
	$('#corpname').val('啃得機')
	$('#owner').val('啃先生')
	$('#industry').val('服務業')
	$('#contact').val('啃小姐')
	$('#comptele').val('0939393939')
	$('#fax').val('07-1325462')
	$('#compaddress').val('新北市土城工業區26號')
	$('#empnumber').val('300')
	$('#website').val('www.giigle.com')
	$('#capital').val('1200萬')
})
