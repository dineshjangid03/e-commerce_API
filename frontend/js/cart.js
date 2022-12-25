
    let user=JSON.parse(localStorage.getItem("user"))
    if(user==null){
        alert("please login")
    window.location.href="index.html"
    }


    viewCart();


    async function clearCart(){
        let uuid=user.uuid;

        let api_link=`http://localhost:8888/cart/clearCart/${uuid}`
        let response=await fetch(api_link,{
            method:"PUT",
            headers:{
                'Content-Type':'application/json'
            }
        })
        let data=await response.json()

        if(data.message!=null){
            alert(data.message);
        }
        else{
            console.log(data)
            viewCart();
        }
    }


    function viewCart(){
        let uuid=user.uuid;
        fetch(`http://localhost:8888/cart/view/${uuid}`)
        .then((response) => response.json())
        .then((data) => displayData(data));
    }


    function displayData(data){
        console.log(data)
        let quantity=document.createElement("p")
        quantity.innerText="total quantity "+data.totalItems;
        quantity.setAttribute("class","quantity")

        let price=document.createElement("p")
        price.innerText="Total ₹ "+data.totalPrice;
        price.setAttribute("class","price")
        document.querySelector("#totalp").innerHTML="";
        document.querySelector("#totalp").append(quantity,price)

        displaymens(data.products);
    }


    function displaymens(mensData){
        document.querySelector("#parent").innerHTML="";
        mensData.forEach(function(el){
        let div=document.createElement("div")
        let imag=document.createElement("img")
        imag.setAttribute("src",el.url)
        imag.setAttribute("class","image")

        let name=document.createElement("p")
        name.innerText=el.productName;
        name.setAttribute("class","name")
        
        let btn1=document.createElement("button")
        btn1.innerText="-"
        let btn2=document.createElement("button")
        btn2.innerText="+"
        btn1.setAttribute("class","butn")
        btn2.setAttribute("class","butn")

        let spn=document.createElement("span")
        
        let quantity=document.createElement("span")
        quantity.innerText=el.quantity;
        quantity.setAttribute("class","quantity")

        let quantity1=document.createElement("span")
        quantity1.innerText="quantity";
        quantity1.setAttribute("class","quantity")

        spn.append(quantity1,btn1,quantity,btn2)

        let available=document.createElement("p")
        if(el.quantity>el.availableProduct){
            available.innerText="out of stock";
        }
        else if(el.availableProduct<=5){
            available.innerText="only "+el.availableProduct+" product left";
        }
        available.setAttribute("class","quantity")

        let price=document.createElement("p")
        price.innerText="price ₹ "+el.price
        price.setAttribute("class","price")

        let tprice=document.createElement("p")
        tprice.innerText="total ₹ "+el.price*el.quantity
        tprice.setAttribute("class","tprice")

        let btn=document.createElement("button")
        btn.innerText="Remove"
        btn.setAttribute("class","delete_from_cart")
        btn.addEventListener("click",function(){
            removeFromCart(el);
        })

        btn2.addEventListener("click",function(){
            incr(el);
        })
        btn1.addEventListener("click",function(){
            decr(el);
        })

        div.append(imag,name,spn,price,tprice,available,btn)
        document.querySelector("#parent").append(div)
        })
    }

    
    async function removeFromCart(el){
        let uuid=user.uuid;
        let pid=el.productId;
        let api_link=`http://localhost:8888/cart/removeItemFromCart/${pid}/${uuid}`
        let response=await fetch(api_link,{
            method:"PUT",
            headers:{
                'Content-Type':'application/json'
            }
        })
        let data=await response.json()

        if(data.message!=null){
            alert(data.message);
        }
        else{
            viewCart();
        }
    }

    
    async function incr(el){
        let uuid=user.uuid;
        let pid=el.productId;
        let api_link=`http://localhost:8888/cart/increaseQuantity/${pid}/${uuid}`
        let response=await fetch(api_link,{
            method:"PUT",
            headers:{
                'Content-Type':'application/json'
            }
        })
        let data=await response.json()

        if(data.message!=null){
            alert(data.message);
        }
        else{
            viewCart();
        }
    }

    
    async function decr(el){
        let uuid=user.uuid;
        let pid=el.productId;
        let api_link=`http://localhost:8888/cart/decreaseQuantity/${pid}/${uuid}`
        let response=await fetch(api_link,{
            method:"PUT",
            headers:{
                'Content-Type':'application/json'
            }
        })
        let data=await response.json()

        if(data.message!=null){
            alert(data.message);
        }
        else{
            viewCart();
        }
    }


    async function checkout(){
        
        let uuid=user.uuid;

        let api_link=`http://localhost:8888/order/addOrder/${uuid}`
        let response=await fetch(api_link,{
            method:"POST",
            headers:{
                'Content-Type':'application/json'
            }
        })
        let data=await response.json()

        if(data.message!=null){
            alert(data.message);
        }
        else{
            alert("order confirmed")
            console.log(data);
        }
    }