async function Register(){
    let key=document.getElementById("key").value;
    let signup_data={
        adminEmail:document.getElementById("email").value,
        adminPassword:document.getElementById("password").value,
        adminMobile:document.getElementById("mobile").value,
    }
    
    signup_data=JSON.stringify(signup_data)
    
    let signup_api_link=`http://localhost:8888/admin/register?validationKey=${key}`
    let response=await fetch(signup_api_link,{
        method:"POST",
        body:signup_data,
        headers:{
            'Content-Type':'application/json'
        }
    })
    let data=await response.json()
    console.log(data)
}


async function Login(){

    let login_data={
      mobile:document.getElementById("mobile1").value,
      password:document.getElementById("password1").value,
    }

    login_data=JSON.stringify(login_data)

    let login_api_link=`http://localhost:8888/adminlogin/login`
    let response=await fetch(login_api_link,{
        method:"POST",
        body:login_data,
        headers:{
            'Content-Type':'application/json'
        }
    })

    let data=await response.json()
    fun(data)

    // localDateTime : "2022-11-27T17:11:33.0402306"
    // userId : 24
    // uuid : "mZKfT0"

}


function fun(data){
  if(data.uuid!=null){
    localStorage.setItem("admin",JSON.stringify(data))
    window.location.href="admin.html"
  }
  else{
    alert(data.message)
  }
}


async function Register1(){
    let signup_data={
        customerName:document.getElementById("name2").value,
        email:document.getElementById("email2").value,
        password:document.getElementById("password2").value,
        mobile:document.getElementById("mobile2").value,
        address: {
          city: "",
          country: "",
          id: 0,
          pincode: "",
          state: ""
        },
    }
    
    signup_data=JSON.stringify(signup_data)
    
    let signup_api_link=`http://localhost:8888/customer/register`
    let response=await fetch(signup_api_link,{
        method:"POST",
        body:signup_data,
        headers:{
            'Content-Type':'application/json'
        }
    })
    let data=await response.json()
    console.log(data)
}


async function Login1(){

    let login_data={
      mobile:document.getElementById("mobile3").value,
      password:document.getElementById("password3").value,
    }

    login_data=JSON.stringify(login_data)

    let login_api_link=`http://localhost:8888/userlogin/login`
    let response=await fetch(login_api_link,{
        method:"POST",
        body:login_data,
        headers:{
            'Content-Type':'application/json'
        }
    })

    let data=await response.json()
    fun1(data)
}


function fun1(data){
  if(data.uuid!=null){
    localStorage.setItem("user",JSON.stringify(data))
    window.location.href="user.html"
  }
  else{
    alert(data.message)
  }
}
