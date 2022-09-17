//first request to server to create order

const paymentStart = () => {
    


    let amount =$("#payment_field").val();

    console.log(amount);

    if(amount =='' || amount == null){

    
        return;
    }

    $.ajax(
        {
            url : "/payment/create_order",
            data:JSON.stringify({amount:amount, info:"order_request"}),
            headers: {
                    'Content-Type':'application/json'
                },
            type:"POST",
            dataType:"json",
            success:function(response){

            },
            error:function(error){
                console.log(error)
                alert("Something went wrong !! ")
            }
        }
    )

};