
    let admin=JSON.parse(localStorage.getItem("admin"))
    if(admin==null){
        alert("please login")
        window.location.href="index.html"
    }
    

    async function viewOrdersByDate(){
        let s=document.getElementById("startdate").value;
        let e=document.getElementById("enddate").value;

        fetch(`http://localhost:8888/order/viewOrdersByDate/${s}/${e}`)
        .then((response) => response.json())
        .then((data) => displayOrd(data));
    }

    async function viewAllOrder(){
        fetch(`http://localhost:8888/order/viewAllOrder`)
        .then((response) => response.json())
        .then((data) => displayOrd(data));
    }

    function displayOrd(data){
        console.log(data)
        if(data.message!=null){
            alert(data.message)
        }
        else{
        let cont=document.getElementById("parent")
        cont.innerHTML=null;
        data.forEach(el => {
            console.log(el)
            let div=document.createElement("div");
            let p1=document.createElement("p");
            p1.innerText="order date : "+el.orderDate
            let p2=document.createElement("p");
            p2.innerText="order time : "+el.orderTime
            let p3=document.createElement("p");
            p3.innerText="order status : "+el.status

            let btn=document.createElement("button")
            btn.innerText="update status"
            btn.addEventListener("click",function(){
                updateStatus(el);
            })
            
            let p4=document.createElement("p");
            p4.innerText="customer name : "+el.customer.customerName

            let p5=document.createElement("p");
            p5.innerText="customer mobile : "+el.customer.mobile

            let p6=document.createElement("p");
            p6.innerText="customer email : "+el.customer.email

            div.addEventListener("click",function(){
                displayData2(el.products);
            })

            div.append(p1,p2,p3,btn,p4,p5,p6)
            cont.append(div)
        });
        }
    }

    async function updateStatus(el){
        let status = prompt("enter status", "confirmed");

        let api_link=`http://localhost:8888/order/updateOrderStatus/${el.orderId}/${status}`

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
            window.location.reload();
        }
    }


    function displayData2(data){

        console.log(data)

        let cont=document.getElementById("display")
        cont.innerHTML=null;

        let table=document.createElement("table");
        let p=document.createElement("p");
        p.setAttribute("class","proCat")
        p.innerText="Products"

        let thead=document.createElement("thead");

        let tr1=document.createElement("tr");

        let th0=document.createElement("th");
        th0.innerText="Image"
        let th1=document.createElement("th");
        th1.innerText="Id";
        let th2=document.createElement("th");
        th2.innerText="Name"
        let th3=document.createElement("th");
        th3.innerText="Description"
        let th4=document.createElement("th");
        th4.innerText="Price"
        let th5=document.createElement("th");
        th5.innerText="Quantity"

        tr1.append(th0,th1,th2,th3,th4,th5);
        thead.append(tr1);

        let tbody=document.createElement("tbody");
        tbody.setAttribute("id","tab1")
        table.append(thead,tbody)
        cont.append(p,table);

        data.forEach(function(ele){
            let tr=document.createElement("tr")
            let td1=document.createElement("td")
            td1.innerText=ele.productId;
            let td2=document.createElement("td")
            td2.innerText=ele.productName
            let td3=document.createElement("td")
            td3.innerText=ele.description
            let td4=document.createElement("td")
            td4.innerText=ele.price;
            let td5=document.createElement("td")
            td5.innerText=ele.quantity

            let td0=document.createElement("td")
            let img=document.createElement("img")
            img.setAttribute("src",ele.url)
            td0.append(img)
            
            tr.append(td0,td1,td2,td3,td4,td5)
            document.getElementById("tab1").append(tr)
            
        });


    }

    viewAllOrder();