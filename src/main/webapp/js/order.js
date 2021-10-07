let select_order_id=0;
$('#addOrder').submit((event)=>{
    event.preventDefault();

    const cusName = $('#cname').val();
    const proName = $('#pname').val();
    const cid = $('#cname').val();
    const count=$('#count').val();
    const bNo=$('#bNo').val();



    const orderObj={
        customer: cusName,
        product:proName,
        order_size: count,
        fis_num: bNo,
    }


    $.ajax({

        url: './add-order-servlet',
        type: 'POST',
        data: { orderObj: JSON.stringify(orderObj) },
        dataType: 'JSON',
        success:function (data){
            allOrderList(cid)
            $("#count").val("")
            $("#pname").val('default');
            $("#pname").selectpicker("refresh");

            if (data>0){
                alert("İslem Basarili")

            }else {
                alert("İslem sirasinda hata olustu!");
            }
        }
    })


})

// Sipariş ekleme bitiş


//Sipariş listeleme başlangıç
function allOrderList(cid){

    $.ajax({
        url: './order-list?cid='+cid,
        type: 'GET',
        dataType: 'Json',

        success: function (data) {
            createRow(data);

        },
        error: function (err) {
            console.log(err)
        }

    })

}

let globalOrder = []
let globalArr = []

let saveObj={

}
function createRow( data ) {

    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        globalOrder.push(itm)
        saveObj.fis_obj_no = itm[3]
        saveObj.cu_obj_no = itm[0]
        saveObj.or_obj_id = itm[7]
        saveObj.price = itm[5]

        html += `<tr role="row" class="odd">
                        <td>`+itm[7]+`</td>
                        <td>`+itm[4]+`</td>
                        <td>`+itm[8]+`</td>
                        <td>`+itm[5]+`</td>
                         <td>`+itm[1]+` `+itm[2]+`</td>
                        <td>`+itm[3]+`</td>
                        <td class="text-right" >
                            <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                                <button id="for_brn" onclick="fncOrderDelete(`+itm[7]+`,`+itm[0]+`)  " type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                            </div>
                        </td>
                    </tr> `;
    }
    $('#tableOrderRow').html(html);
}


$("#cname").on("change",function (){
    allOrderList(this.value);
    codeGenerator();


})
//Listeleme bitiş



function fncOrderDelete( order_id ,cid) {
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './delete-order?order_id='+order_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                allOrderList(cid)
                if ( data != "0" ) {

                }else {
                    alert("Silme sırasında bir hata oluştu!");
                }
            },
            error: function (err) {
                console.log(err)
            }
        })
    }

}



function codeGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#bNo').val( key )

}
function fncObjc(){
    console.log(globalOrder)
    const item=globalOrder
    console.log(saveObj)

    $.ajax({
        url: './osChange?cid='+saveObj.fis_obj_no,
        type: 'GET',
        data: { saveObj: JSON.stringify(saveObj) },
        dataType: 'Json',

        success: function (data) {

            alert("İslem basarili")

        },
        error: function (err) {
            console.log(err)
        }

    })


    location.reload();

}





