fetch('http://localhost:8888/product/top5')
.then((response) => response.json())
.then((data) => displayData(data));


fetch('http://localhost:8888/category/viewAll')
.then((response) => response.json())
.then((data) => displayData1(data));


function displayData(data){

    data.forEach(function(ele){
        let tr=document.createElement("tr")
        let td1=document.createElement("td")
        let img=document.createElement("img")
        img.setAttribute("src",ele.url)
        td1.append(img)
        let td2=document.createElement("td")
        td2.setAttribute("class","mid")
        td2.innerText=ele.productName
        let td3=document.createElement("td")
        td3.innerText=ele.soldCount

        tr.append(td1,td2,td3)
        document.getElementById("tab").append(tr)
        // document.querySelector("tbody").append(tr)
        
    });

}

function displayData1(data){
console.log(data)
data.forEach(function(ele){
    let tr=document.createElement("tr")
    let td1=document.createElement("td")
    td1.innerText=ele.categoryId;
    let td2=document.createElement("td")
    td2.setAttribute("class","mid")
    td2.innerText=ele.name
    let td3=document.createElement("td")
    td3.innerText=ele.products.length
    
    tr.append(td1,td2,td3)
    document.getElementById("tab1").append(tr)
    // document.querySelector("tbody").append(tr)
    
});

}