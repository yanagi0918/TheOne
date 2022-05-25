//Form rule
$(function () {

	$('#form').validate({
		rules: {
			user: {
				required: true,
			},

			user_id: {
				required: true,
			},
			job_name: {
				required: true,
			},
			job_description: {
				required: true,
			},
			std_hour: {
				required: true,
				number: true,
				range: [0, 24],
			},
			real_hour: {
				required: true,
				number: true,
				range: [0, 24],
			},
			over_freq: {
				required: true,
				digits: true,
				range: [0, 7],
			},
			seniority: {
				required: true,
				number: true,
				range: [0, 100],
			},
			total_seniority: {
				required: true,
				number: true,
				range: [0, 100],
			},
			monthly_salary: {
				required: true,
				digits: true,
				min: 0,
			},
			yearly_salary: {
				required: true,
				number: true,
				min: 0,
			},
			bonus_count: {
				required: true,
				digits: true,
				min: 0,
			},
		},
		messages: {
			job_description: {
				required: '請選擇類別',
			},
			monthly_salary: {
				digits: '請輸入大於0的整數',
			},
		},
	})

})

//Star rating js
$.raty.path = 'img';

$(function () {

	$('#compScore').raty({
		targetScore: '#comp_score',
	});

	$('#jobScore').raty({
		targetScore: '#job_score',
	});

	$('#oneInput').raty({
		score: 5
	});

	$('td.listComp').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('td.listJob').raty({
		readOnly: true,
		starOff: 'star-off-small.png',
		starOn: 'star-on-small.png'
	});

	$('#confirmJob').raty({
		readOnly: true
	});

	$('#confirmComp').raty({
		readOnly: true
	});
})


//One key input js
$('#OneInput').click(function () {
	$('#user_id').val('A123456789')
	$('#comp_name').val('狗來富寵物廣場')
	//$(':input #comp_score').val('5');
	//$('#compScore').attr('data-score', '5')

	$('#job_name').val('美容師')
	$('#job_description option[value="全職"]').attr('selected', 'selected')

	$('#std_hour').val('10')
	$('#real_hour').val('12')
	$('#over_freq').val('2')
	$('#seniority').val('5')
	$('#total_seniority').val('7')
	$('#seniority').val('5')
	$('#monthly_salary').val('40000')
	$('#yearly_salary').val('55')
	$('#bonus_count').val('2')
	$('#share').val('老闆親切，加班可報')
})

//anonymous/user show
/*
$(function () {
	$('#anonymous').click(function () {
		$('#user_id').val('匿名');
		$('#user_id').hide();
	})

	$('#user').click(function () {
		$('#user_id').show();
	});

})
*/

//Sorting

function sortTable(n) {
	var table,
		rows,
		switching,
		i,
		x,
		y,
		shouldSwitch,
		dir,
		switchcount = 0;
	table = document.getElementById("myTable");
	switching = true;
	//Set the sorting direction to ascending:
	dir = "asc";
	/*Make a loop that will continue until
	no switching has been done:*/
	while (switching) {
		//start by saying: no switching is done:
		switching = false;
		rows = table.getElementsByTagName("TR");
		/*Loop through all table rows (except the
		first, which contains table headers):*/
		for (i = 1; i < rows.length - 1; i++) { //Change i=0 if you have the header th a separate table.
			//start by saying there should be no switching:
			shouldSwitch = false;
			/*Get the two elements you want to compare,
			one from current row and one from the next:*/
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/*check if the two rows should switch place,
			based on the direction, asc or desc:*/
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					//if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/*If a switch has been marked, make the switch
			and mark that a switch has been done:*/
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			//Each time a switch is done, increase this count by 1:
			switchcount++;
		} else {
			/*If no switching has been done AND the direction is "asc",
			set the direction to "desc" and run the while loop again.*/
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}