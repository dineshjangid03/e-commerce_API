let user=JSON.parse(localStorage.getItem("user"))
if(user==null){
    alert("please login")
  window.location.href="index.html"
}


async function viewProByName(){
    event.preventDefault()
    let key=document.getElementById("pro").value;

    fetch(`http://localhost:8888/product/productByName/${key}`)
    .then((response) => response.json())
    .then((data) => displaymens(data));
}


async function viewAllPro(){

fetch('http://localhost:8888/product/viewAllProduct')
.then((response) => response.json())
.then((data) => displaymens(data));

}


viewAllPro()


function displaymens(mensData){
document.querySelector("#parent").innerHTML="";
mensData.forEach(function(el){

let div=document.createElement("div")
div.setAttribute("class","product")

let imag=document.createElement("img")
imag.setAttribute("src",el.url)
imag.setAttribute("class","image")

let name=document.createElement("h3")
name.innerText=el.productName;
name.setAttribute("class","name")

let desc=document.createElement("p")
desc.innerText=el.description;
desc.setAttribute("class","desc")

let price=document.createElement("p")
price.innerText="₹"+el.price
price.setAttribute("class","price")

let btn=document.createElement("button")
btn.innerText="Add to Cart"
btn.setAttribute("class","add_to_cart")
btn.addEventListener("click",function(){
  btn.disabled=true
  btn.innerText="Go to Cart"
      addToCart(el)
    })

div.append(imag,name,desc,price,btn)
document.querySelector("#parent").append(div)
})
}


async function addToCart(el){

    let pid=el.productId;
    let uuid=user.uuid;

    let api_link=`http://localhost:8888/cart/addItemIntoCart/${pid}/${uuid}`

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
    }

}


async function viewAllCat(){
    fetch('http://localhost:8888/category/viewAll')
    .then((response) => response.json())
    .then((data) => discat(data));
}


function discat(data){
    document.querySelector("#parent").innerHTML="";
    data.forEach(e => {
        let div=document.createElement("div");
        div.innerText=e.name

        div.addEventListener("click",function(){
            displaymens(e.products);
        })

        document.getElementById("parent").append(div)
    });
}


async function viewOrder(){
    let uuid=user.uuid;
    fetch(`http://localhost:8888/customer/viewOrders/${uuid}`)
    .then((response) => response.json())
    .then((data) => disOrder(data));
}


function disOrder(data){
    console.log(data)
    document.querySelector("#parent").innerHTML="";
    data.forEach(e => {
        let div=document.createElement("div");
        let p1=document.createElement("p");
        p1.innerText="Order date : "+e.orderDate
        let p2=document.createElement("p");
        p2.innerText="Order time : "+e.orderTime
        let p3=document.createElement("p");
        p3.innerText="Order status : "+e.status
        let p4=document.createElement("p");
        p4.innerText="total products : "+e.products.length
        div.append(p1,p2,p3,p4)

        div.addEventListener("click",function(){
            displayOrder(e.products);
        })

        document.getElementById("parent").append(div)
    });
}


function displayOrder(mensData){
    console.log(mensData)
document.querySelector("#parent").innerHTML="";
mensData.forEach(function(el){
    
let div=document.createElement("div")
let imag=document.createElement("img")
imag.setAttribute("src",el.url)
imag.setAttribute("class","image")

let name=document.createElement("p")
name.innerText=el.productName;
name.setAttribute("class","name")

let price=document.createElement("p")
price.innerText="₹"+el.price
price.setAttribute("class","price")

let q=document.createElement("p")
q.innerText="quantity "+el.quantity
q.setAttribute("class","price")

let btn=document.createElement("button")

div.append(imag,name,price,q,btn)
document.querySelector("#parent").append(div)
})
}
