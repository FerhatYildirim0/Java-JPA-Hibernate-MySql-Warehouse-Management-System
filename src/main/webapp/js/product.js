//Products
//Ürün ekleme başlangıç
let select_pidx = 0
$('#product_add_form').submit( ( event ) => {
    event.preventDefault();

    const ptitle = $("#ptitle").val()
    const aprice = $("#aprice").val()
    const oprice = $("#oprice").val()
    const pcode = $("#pcode").val()
    const ptax = $("#ptax").val()
    const psection = $("#psection").val()
    const size = $("#size").val()
    const pdetail = $("#pdetail").val()

    const pro_obj = { //Key ve Value lar
        pro_title: ptitle,
        pro_buying_price: aprice,
        pro_sale_price: oprice,
        pro_code: pcode,
        pro_tax_status: ptax,
        pro_unit_status: psection,
        pro_amount: size,
        pro_detail: pdetail,
    }
    if( select_pidx !=0 ){
        pro_obj["pro_id"] = select_pidx;
    }
$.ajax({
     url:'./product-insert', //Servlet ismi tanımlanır
     type: 'POST',
     data: { pro_obj: JSON.stringify(pro_obj) },
     dataType: 'JSON',
     success: function (pro_data) {
        if ( pro_data > 0 ) {
            alert("Ürün ekleme başarılı")
            fncProReset();

        }else {
            alert("Ürün ekleme sırasında hata oluştu!");
        }
    },
    error: function (err) {
        console.log(err)
        alert("Ürün ekleme işlemi sırasında bir hata oluştu!");
    }

})
    fncProReset();
})
//Ürün ekleme bitiş

// Ürün listeleme başlangıç
function allProduct() {

    $.ajax({
        url: './product-update',
        type: 'GET',
        dataType: 'JSON',
        success: function (pro_data) {
            createRow(pro_data);
        },
        error: function (err) {
            console.log(err)
        }
    })

}

let productArr = []
function createRow( pro_data ) {
    productArr = pro_data;
    let html = ``
    for (let j = 0; j < pro_data.length; j++) {
        const item = pro_data[j];
        let ts = '';
        let us = '';
       if(item.pro_tax_status==0){
          ts = 'Dahil'
       }
       else if(item.pro_tax_status==1){
           ts = '%1'
        }

       else if(item.pro_tax_status==2){
           ts = '%8'
        }
       else if(item.pro_tax_status==3){
           ts = '%18'
        }



        if(item.pro_unit_status==0){
            us = 'Adet'
        }
        else if(item.pro_unit_status==1){
            us = 'KG'
        }

        else if(item.pro_unit_status==2){
            us = 'Metre'
        }
        else if(item.pro_unit_status==3){
            us = 'Paket'
        }
        else if(item.pro_unit_status==4){
            us = 'Litre'
        }


        html += `<tr role="row" class="odd">
            <td>` + item.pro_id + `</td>
            <td>` + item.pro_title + `</td>
            <td>` + item.pro_buying_price + `</td>  
            <td>` + item.pro_sale_price + `</td>  
            <td>` + item.pro_code + `</td>  
            <td>` + ts + `</td>                        
            <td>` + us + `</td>
            <td>` + item.pro_amount + `</td>
            <td class="text-right" >
              <div class="btn-group" role="group" aria-label="Basic mixed styles example">             
               <button onclick="fncProductDelete(` + item.pro_id + `)" type="button" class="btn btn-outline-primary "><i class="far fa-trash-alt"></i></button>
               <button onclick="fncProductDetail(`+j+`)" data-bs-toggle="modal" data-bs-target="#productDetailModel" type="button" class="btn btn-outline-primary "><i class="far fa-file-alt"></i></button>
               <button onclick="fncProductUpdate(`+j+`)" type="button" class="btn btn-outline-primary "><i class="fas fa-pencil-alt"></i></button>
              </div>
            </td>
          </tr>`;
    }

    $('#tableProRow').html(html);
}
function codeProGenerator() {
    const date = new Date();
    const time = date.getTime();
    const key = time.toString().substring(4);
    $('#pcode').val( key )
}
allProduct();
// Ürün listeleme bitiş

// Temizle aksiyonu
function fncProReset() {
    select_pidx = 0;
    $('#product_add_form').trigger("reset");
    codeProGenerator();
    allProduct();
}

// Ürün silme başlangıç
function fncProductDelete( pro_id ) {
    let answer = confirm("Ürünü silmek istediğinizden emin misniz?");
    if ( answer ) {

        $.ajax({
            url: './product-delete?pro_id='+pro_id,
            type: 'DELETE',
            dataType: 'text',
            success: function (pro_data) {
                if ( pro_data != "0" ) {
                    fncProReset();
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
// Ürün silme bitiş

// Ürün Güncelleme Seçimi
function fncProductUpdate( j ) {
    const item = productArr[j];
    select_pidx = item.pro_id
    $("#ptitle").val(item.pro_title)
    $("#aprice").val(item.pro_buying_price)
    $("#oprice").val(item.pro_sale_price)
    $("#pcode").val(item.pro_code)
    $("#ptax").val(item.pro_tax_status)
    $("#psection").val(item.pro_unit_status)
    $("#size").val(item.pro_amount)
    $("#pdetail").val(item.pro_detail)


}

function fncProductDetail(i){
    const itm = productArr[i];
    $("#pro_title").text(itm.pro_title + " " + " - " + itm.pro_id); //item içindeki pro_title ı jspde pro_title a atar
    $("#pro_buying_price").text(itm.pro_buying_price == "" ? '------' : itm.pro_buying_price);
    $("#pro_sale_price").text(itm.pro_sale_price == "" ? '------' : itm.pro_sale_price);
    $("#pro_code").text(itm.pro_code == "" ? '------' : itm.pro_code);
    $("#pro_amount").text(itm.pro_amount == "" ? '------' : itm.pro_amount);
    $("#pro_detail").text(itm.pro_detail == "" ? '------' : itm.pro_detail);

}