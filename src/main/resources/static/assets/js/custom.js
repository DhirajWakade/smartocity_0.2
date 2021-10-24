var BUSINESS_FILED_API="/restAdmin/getBusinessField/";
var SAVE_BUSINESS_FILED_POST_API="/restAdmin/updateBusinessFieldToBusiness";

function getBASE_URL()
{
		var getUrl = document.location + '';    
		const myArr = getUrl.split("/");
		var API_URL=myArr[0]+"//"+myArr[2]+"/"+myArr[3];	  
  		return API_URL;
}

function getBusinessFieldByTypeId(businessTypeId)
{
	var API_URL=getBASE_URL()+BUSINESS_FILED_API+businessTypeId;
	$.get( API_URL, function( data ) {
  	var	response=JSON.stringify(data);
alert("response="+response);
	return response;
	});
	
}

	
$( "#exampleFormControlSelect2" ).change(function(){
	
  		var businessTypeId = "";
      businessTypeId=$("select#exampleFormControlSelect2 option").filter(":selected").val();
		
		var API_URL=getBASE_URL()+BUSINESS_FILED_API+businessTypeId;
		$.get( API_URL, function( data ) {
	  	var	response=JSON.stringify(data);
		var result=data['result'];
		if(result.length<=0){
		alert("No Record Found");
		}
		for (var i = 0; i < result.length; i++)
		 {	    		
            var fieldname=result[i].businessField['fieldName'];
			var fieldCode=result[i].businessField['fieldCode'];
			var fId=result[i].businessField['fId'];
			var visible=result[i]['isVisible'];
			var fieldId=result[i]['fieldId'];
			var mandaryOrOptional=result[i]['mandaryOrOptional'];
			    console.log("visible="+visible);

 			$("#MAN"+fId).val(mandaryOrOptional).attr("selected", "selected");
			$("#VISI"+fId).val(visible).attr("selected", "selected");
			//$("#SelectPayment option:selected").val('Cash');
			var id=fieldCode.replace('(', '').replace(')','').replace('.','').replace('?','');  
			  
			$("#"+id).text(fieldId); 
		}//for end
		});
    //});	
});


/// Apply Button
$(document).ready(function(){

 $("#myTable").on('click', '.btn-business-field-apply', function() 
   {
	
  	var currentRow = $(this).closest("tr");
  // get the current row
	
	var tdObject = currentRow.find('td:eq(2)');
    var selectObject = tdObject.find("select"); 
    var visible = selectObject.val();

	var tdManObject = currentRow.find('td:eq(3)');
    var selectManObject = tdManObject.find("select"); 
    var mandantory = selectManObject.val();
	
	var filedId = currentRow.find(".FIEILD_ID").html();

	var API_URL=getBASE_URL()+SAVE_BUSINESS_FILED_POST_API;
var myData={};
myData["fieldId"]=filedId;
myData["isVisible"]=visible;
myData["mandaryOrOptional"]=mandantory;

console.log("myData="+JSON.stringify(myData));


$.ajax({
        url:API_URL,
		type: "POST",
        contentType: "application/json",
        dataType: 'json',
		data:JSON.stringify(myData),
        success: function(result){
            if(result.status==1)
			{
				alert("Successfully");
			}
			else
			{
				{
				alert("Failed ..Try Again");
			}
			}
        }
    })

});
});


$('#myTable1 tbody tr').each(function() { 
	var currentRow = $(this).closest("tr");  
    
});


function visiChange(selectedOption)
{
    let idLet = selectedOption.id;
	var no=idLet.substring(4, idLet.length);  
	$('#BOTTON'+no).removeAttr("disabled");    
   
}
function manChange(selectedOption)
{
	let idLet = selectedOption.id;
	var no=idLet.substring(3, idLet.length);  
	$('#BOTTON'+no).removeAttr("disabled");  
}





