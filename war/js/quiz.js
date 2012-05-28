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

function getNextLetter(i){
	
	correct = $('#selectedIndices'+i)[0].value;
	lenCorrect = correct.length;
	guess =  $('#inputfield'+i)[0].value;
	hint = '';
	index=-1;
	if(guess.length>0){

		index = correct.indexOf(guess);
		while(index != 0 && guess.length>1){
			guess = guess.substring(0,guess.length-1); 
			index = correct.indexOf(guess);
		}
	}
	
	if( index==0){
		lenGuess = guess.length;
		
	} else{
		lenGuess = 0;
	}
	if( lenCorrect>lenGuess){
		hint = correct.substring(0,lenGuess+1);
	}	
	$('#inputfield2')[0].value = hint;
	if(lenCorrect == lenGuess+1 ){
		$('#inputfield2').addClass('correct');
	}
}