function init(){
	$('#removeSelected').hide();
	$('#selectedArea').hide();	 
	$('#newContextForm').hide();
	$('#splittedText').hide();
	
}

function splitText()
{
  var text = $('#inputarea textarea')[0].value;
  reset();	

  splitAndGetContainerForText(text);
  
  showElements();
  hideElements();
  setDroppable();
  setDroppableDustbin();
}

function hideElements(){

	$('#splitButton').hide();
	
}
function showElements(){

	$('#newContextForm').show(600);
	$('#selectedArea').show(600);
	$('#removeSelected').show(600);
	$('#splittedText').show(600);
}

function setDroppableDustbin(){

	
	$( '#removeSelected' ).droppable( {
     accept: '#selectedAreaList li',
     hoverClass: 'hovered',
     drop: handleDustbin
   } );
}

function handleDustbin( event, ui ) {

	var number = ui.draggable.data( 'number' );
	ui.draggable.hide("slow", function(){ $(this).remove(); });
	
	var test = $('#inputarea #splittedText span:data("number='+ number +'")');
	test.removeClass('selected');
	
}
function reset(){
	
	$('#inputarea textarea').remove();	
}

function setDroppable(){
	
	$( '#selectedArea' ).droppable( {
     accept: '#inputarea #splittedText span',
     hoverClass: 'hovered',
     drop: handleCardDrop
   } );
}

function findWordInArray(index, arrSplitted){
	var len=arrSplitted.length;
	for(var i=0; i<index; i++) {
		if(arrSplitted[i] == arrSplitted[index]){
			return i;
		}
	}
	return -1;
}

function splitAndGetContainerForText(text){
		

	/*split text*/
	var arrSplitted =text.split(" "); 
	
	var len=arrSplitted.length;
	
	for(var i=0; i<len; i++) {
    
		var word = arrSplitted[i];
		
		var prefix = "";
		var suffix = "";
		
		var indexSpecialChar = word.indexOf(".");
		if( indexSpecialChar >= 0 ){
			if(indexSpecialChar == 0){
				prefix = prefix.concat(".");
				word = word.substring(1);	
			} else {
				suffix = suffix.concat(".");
				word = word.substring(0,indexSpecialChar);
			}
		}
		
		indexSpecialChar = word.indexOf(",");
		if( indexSpecialChar >= 0 ){
			if(indexSpecialChar == 0){
				prefix = prefix.concat(",");
				word = word.substring(1);	
			} else {
				suffix = suffix.concat(",");
				word = word.substring(0,indexSpecialChar);
			}
		}
		
		indexSpecialChar = word.indexOf("\n");
		if( indexSpecialChar >= 0 ){
			if(indexSpecialChar == 0){
				prefix = prefix.concat("\n");
				word = word.substring(1);	
			} else {
				suffix = suffix.concat("\n");
				word = word.substring(0,indexSpecialChar);
			}
		} 		
		
		var indexFound = findWordInArray(i,arrSplitted);
		
		if(indexFound == -1){
			indexFound=i;
		}
		$("#splittedText").append(prefix);
	    $('<span>' + word + '</span>').data( 'number', indexFound ).data('word', word).attr( 'class', 'item word'+indexFound ).appendTo( '#splittedText' ).draggable( {
				helper: 'clone',
				cursor: 'move',
				revert: true
	    } );
	    
	    $("#splittedText").append( suffix + " ");
	}
	
}

function handleCardDrop( event, ui ) {
	
	var word = ui.draggable.data( 'word' );
	var number = ui.draggable.data( 'number' );
	
	ui.draggable.draggable( 'option', 'revert', false );
    ui.draggable.addClass( 'selected' );
	
	var test = $('li');
	 test = $('li:data("word='+ word +'")');

	if(test.length == 0 ){
	  $('<li>' + word + '</li>').data('word', word).data('number', number).appendTo( '#selectedAreaList' ).draggable( {
	      cursor: 'move',
	      revert: true
	    } );
	}
	
}

function collectInformation(contextname){
	var items = $('#inputArea #splittedText span.item');	
	var itemslen = items.length;	
	var vocab = new Array(itemslen);
	var selectedIndices = [];
	
	for(var i=0, index=0; i<itemslen; i++){
		vocab[i] = items[i].innerHTML;
		var b = $(items[i]).hasClass('selected');
		if( b ){
			selectedIndices[index++]=i;
		}
	}
	
	$('#contextName').val(contextname);
	$('#vocabularyList').val(vocab);
	$('#selectedIndices').val(selectedIndices);
}
