let select_pidi = 0
$('#payAdd').submit((event)=>{
    event.preventDefault();

    const cu_id=$('#cu_id').val()
    const order_id=$('#order_id').val()
    const payInTotal=$('#payInTotal').val()
    const payInDetail=$('#payInDetail').val()



    const obj={
        cu_id:cu_id,
        order_id:order_id,
        payInTotal:payInTotal,
        payInDetail:payInDetail

    }

    if ( select_pidi != 0 ) {
        obj["cash_id"] = select_pidi;
    }

    $.ajax({

        url: './add-payin',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success:function (data){
            allPayInList();
            if (data>0){
                alert("İslem Basarili")

            }else {
                alert("İslem sirasinda hata olustu!");
            }
        }
    })
    allPayInList();
})
fncResetPayIn();


function allPayInList(){

    $.ajax({
        url: './get-payin',
        type: 'GET',
        dataType: 'JSON',

        success: function (data) {
            createRow(data);

        },
        error: function (err) {
            console.log(err)
        }

    })

}
allPayInList();
let globalArr = []
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];

        html += `<tr role="row" class="odd">
            <td>`+itm[0]+`</td>
            <td>`+itm[1]+`</td>
            <td>`+itm[2]+`</td>
            <td>`+itm[3]+`</td>
            <td>`+itm[4]+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncPayinDelete(`+itm[0]+`)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
                <button onclick="fncPayinDetail(`+i+`)" data-bs-toggle="modal" data-bs-target="#customerDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
                <button onclick="fncPayinUpdate( `+i+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }
    $('#tablePayInRow').html(html);
}
allPayInList();



function fncPayinDelete( cu_id ) {
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './delete-payin?cash_id='+cu_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                if ( data != "0" ) {
                    fncReset();
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


allPayInList();

function fncPayinDetail(i){
    const itm = globalArr[i];
    $("#cash_id").val(itm[5])
    $("#cu_name").val(itm[7])
    $("#cash-detail").val(itm[6])
    $("#fis_nox").val(itm[3])
    $("#cash-amounth").val(itm[4])
    $("#cu_name_detail").text(itm[7]);
    $("#cu_email").text(itm[5]);
    $("#cu_company_title").text(itm[6]);
    $("#cu_code").text(itm[3]);


}

allPayInList();

function fncPayinUpdate( i ) {
    const itm = globalArr[i];
    select_id = itm[2]
    $("#cu_id").val(itm[5])
    $("#order_id").val(itm[7])
    $("#payInTotal").val(itm[4])
    $("#payInDetail").val(itm[6])

}

function fncResetPayIn() {
    select_pido= 0;
    $('#payAdd').trigger("reset");
    allPayInList();
}