$(document).ready(function(){
	$('.inputtext').keyup(clickHandler);
})

function clickHandler(){
	var correct = $(this).prev().val();
	if(correct == $(this).val()){
		$(this).addClass('correct');
	} else {
		$(this).removeClass('correct');
	}
}

function askMeaning( word){
	alert(word);
//	$.getJSON(
//			"/hint.json?word='"+word+"'",
//			null,
//			function(data){
//				$('#context').loadSelect(data);
//			}
//		);
}