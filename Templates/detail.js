const orderId = document.getElementById("orderid");
const customerName = document.getElementById("customernames");
const food = document.getElementById("foodItem");
const quantity = document.getElementById("quantity");
const addOrder = document.getElementById("addOrder");
const tableBody = document.querySelector("#orderTable tbody");

addOrder.addEventListener("click",async function() {
    if (
        orderId.value === "" ||
        customerName.value === "" ||
        food.value === "" ||
        quantity.value === ""
    ) {
        alert("Fill all fields");
        return;
    }
    const order = {
        orderId: Number(orderId.value),
        customerName: customerName.value,
        foodItem: food.value,
        quantity: Number(quantity.value)
    };

    let tr =document.createElement("tr");

    tr.innerHTML=`
        <td>${orderId.value}</td>
        <td>${customerName.value}</td>
        <td>${food.value}</td>
        <td>${quantity.value}</td>
    `;

    tableBody.appendChild(tr);

    try {
        const savedOrder = await saveOrderToServer(order);
        console.log(savedOrder);
    } catch (error) {
        alert("Order failed");
    }
    orderId.value = "";
    customerName.value = "";
    food.value = "";
    quantity.value = "";
});

async function saveOrderToServer(order) {
    const response = await fetch('http://localhost:8080/api/orders',{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(order),
    });
    if(!response.ok) {
        throw new Error("Failed to save order");
    }

    return await response.json();
}


