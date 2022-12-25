
    let user=JSON.parse(localStorage.getItem("user"))
    if(user==null){
        alert("please login")
    window.location.href="index.html"
    }


    viewPro();


    function viewPro(){
        let uuid=user.uuid;

        fetch(`http://localhost:8888/customer/view/${uuid}`)
        .then((response) => response.json())
        .then((data) => disPro(data));
    }


    async function logout(){
        let uuid=user.uuid;

        let api_link=`http://localhost:8888/userlogin/logout?key=${uuid}`
        let response=await fetch(api_link,{
            method:"POST",
            headers:{
                'Content-Type':'application/json'
            }
        })
        localStorage.setItem("user",null)
        window.location.reload()
    }

    
    function disPro(data){
        let div=document.getElementById("viewcustomer");
        
        let p1=document.createElement("p");
        p1.innerText="Name"
        let inp1=document.createElement("input")
        inp1.value=data.customerName
        inp1.setAttribute("id","name")
        
        let p2=document.createElement("p");
        p2.innerText="Email"
        let inp2=document.createElement("input")
        inp2.value=data.email
        inp2.setAttribute("id","email")
        
        let p3=document.createElement("p");
        p3.innerText="Mobile"
        let inp3=document.createElement("input")
        inp3.value=data.mobile
        inp3.setAttribute("id","mobile")
        
        let p4=document.createElement("p");
        p4.innerText="Password"
        let inp4=document.createElement("input")
        inp4.value=data.password
        inp4.setAttribute("id","password")


        let p5=document.createElement("p");
        p5.innerText="City"
        let inp5=document.createElement("input")
        inp5.value=data.address.city
        inp5.setAttribute("id","city")
        
        let p6=document.createElement("p");
        p6.innerText="State"
        let inp6=document.createElement("input")
        inp6.value=data.address.state
        inp6.setAttribute("id","state")
        
        let p7=document.createElement("p");
        p7.innerText="Pincode"
        let inp7=document.createElement("input")
        inp7.value=data.address.pincode
        inp7.setAttribute("id","pincode")
        
        let p8=document.createElement("p");
        p8.innerText="Country"
        let inp8=document.createElement("input")
        inp8.value=data.address.country
        inp8.setAttribute("id","country")

        div.append(p1,inp1,p2,inp2,p3,inp3,p4,inp4,p5,inp5,p6,inp6,p7,inp7,p8,inp8)
    }


    async function updateDetail(){

        let key=user.uuid;

        let pro_data={
            address:{
                city:document.getElementById("city").value,
                country:document.getElementById("country").value,
                pincode:document.getElementById("pincode").value,
                state:document.getElementById("state").value,
            },
            customerName:document.getElementById("name").value,
            email:document.getElementById("email").value,
            mobile:document.getElementById("mobile").value,
            password:document.getElementById("password").value,
        }

        pro_data=JSON.stringify(pro_data)

        let api_link=`http://localhost:8888/customer/update?key=${key}`
        let response=await fetch(api_link,{
            method:"PUT",
            body:pro_data,
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
