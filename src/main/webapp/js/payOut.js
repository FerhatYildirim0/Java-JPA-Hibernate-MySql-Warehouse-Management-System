
$('#payOutAdd').submit((event)=>{
    event.preventDefault();

    const payOutTitle=$('#payOutTitle').val()
    const payOutType=$('#payOutType').val()
    const payOutTotal=$('#payOutTotal').val()
    const payOutDetail=$('#payOutDetail').val()

    const obj={

        payOutTitle:payOutTitle,
        payOutSection:payOutType,
        payOutDetail:payOutDetail,
        payOutTotal:payOutTotal

    }


    $.ajax({

        url: './add-payout',
        type: 'POST',
        data: { obj: JSON.stringify(obj) },
        dataType: 'JSON',
        success:function (data){
            allPayOutList()
            if (data>0){
                alert("İslem Basarili")
                fncResetPayOut();

            }else {
                alert("İslem sirasinda hata olustu!");
            }
        }
    })
    fncResetPayOut();
})


function allPayOutList(){

    $.ajax({
        url: './get-payout',
        type: 'GET',
        dataType: 'Json',

        success: function (data) {
            createRow(data);


        },
        error: function (err) {
            console.log(err)
        }

    })
    fncResetPayOut();
}

let globalArr = []
function createRow( data ) {
    globalArr = data;
    let html = ``
    for (let i = 0; i < data.length; i++) {
        const itm = data[i];
        console.log(data)

        html += `<tr role="row" class="odd">
            <td>`+itm[0]+`</td>
            <td>`+itm[1]+`</td>
            <td>`+itm[2]+`</td>
            <td>`+itm[3]+`</td>
            <td>`+itm[4]+`</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                <button onclick="fncPayOutDelete()" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }
    $('#tablePayOutRow').html(html);
}
allPayOutList();

function fncPayOutDelete( cashOut_id ) {
    let answer = confirm("Silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './delete-payout?cashOut_id='+cashOut_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (data) {
                if ( data != "0" ) {
                    fncResetPayOut();
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

function fncResetPayOut() {
    $('#payOutAdd').trigger("reset");
    allPayOutList();
}