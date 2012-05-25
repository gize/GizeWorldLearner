function init(){
	var test = $('#removeSelected');
	test.hide();
}

function splitText()
{
	var text = $('#inputarea textarea')[0].value;
   
	reset();	
	
  splitAndGetContainerForText(text);
  
  setDroppable();
  setDroppableDustbin();

}

function setDroppableDustbin(){
	$('#removeSelected').show(600);
	  
	$( '#removeSelected' ).droppable( {
     accept: '#selectedAreaList li',
     hoverClass: 'hovered',
     drop: handleDustbin
   } );
}

function handleDustbin( event, ui ) {

	var number = ui.draggable.data( 'number' );
	ui.draggable.hide("slow", function(){ $(this).remove(); });
	
	var test = $('#inputarea span:data("number='+ number +'")');
	test.removeClass('selected');
	
}
function reset(){
	
	$('#inputarea').empty();	
}

function setDroppable(){
	
	$( '#selectedArea' ).droppable( {
     accept: '#inputarea span',
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
    
		var indexFound = findWordInArray(i,arrSplitted);
		
		if(indexFound == -1){
			indexFound=i;
		}
	    $('<span>' + arrSplitted[i] + '</span>').data( 'number', indexFound ).data('word', arrSplitted[i]).attr( 'class', 'item word'+indexFound ).appendTo( '#inputarea' ).draggable( {
				helper: 'clone',
	      cursor: 'move',
	      revert: true
	    } );
	    
	    $("#inputarea").append(" ");
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

function collectInformation(){
	var items = $('#inputArea span.item');	
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
	
	$('#vocabularyList').val(vocab);
	$('#selectedIndices').val(selectedIndices);
}
