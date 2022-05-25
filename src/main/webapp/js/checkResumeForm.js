function checkResumeForm() {
	let checkResumeForm = true;

	let userID = /^[A-Z]{1}[1-2]{1}\d{8}$/;
	if (!userID.test($("#USER_ID").val())) {
		Swal.fire('Warning!',
			'身分證格式錯誤!',
			'warning');
		checkResumeForm = false;
		return checkResumeForm;
	}
}