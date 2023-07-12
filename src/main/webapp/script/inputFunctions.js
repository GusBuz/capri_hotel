function phoneFormatter(input) {
    let cellphone = input.value.replace(/\D/g, '');
    cellphone = cellphone.replace(/^(\d{2})(\d)/g, '($1) $2');
    cellphone = cellphone.replace(/(\d)(\d{4})$/, '$1-$2');
    input.value = cellphone
}

function numberValidation(){
    const inputs = document.getElementsByTagName('input');
    const phone = document.getElementById("phone").value;
    const cpf = document.getElementById("cpf").value;

    if (phone.length < 14 || cpf.length < 14){
        alert("Telefone ou CPF incompletos. Por favor, preencha todos os campos corretamente");
        return false;
    }
    for (let i = 0; i < inputs.length; i++){
        if (inputs[i].value === '') {
            alert("Por favor, preencha todos os campos corretamente.");
            return false;
        }
    }
    return true;
}

function dateValidation(){
    const inputs = document.getElementsByTagName('input');
    const checkin = document.getElementById("checkinDate").value.trim();
    const checkout = document.getElementById("checkoutDate").value.trim();

    if (checkin === "" || checkout === "") {
        alert("Datas incompletas. Por favor, preencha todos os campos corretamente.");
        return false;
    }

    for (let i = 0; i < inputs.length; i++){
        if (inputs[i].value === '') {
            alert("Por favor, preencha todos os campos corretamente.");
            return false;
        }
    }
    return true;
}

function nameFormatter(input){
    let name = input.value;
    name = name.replace(/[^a-zA-ZÀ-ÿ ]/g, '');
    // name = name.replace(/(?:^|\s)\S/g, function(l){
    //     return l.toUpperCase();
    // });
    input.value = name;
}

function updateValue() {
    const checkin = document.getElementById("checkinDate").value;
    const checkout = document.getElementById("checkoutDate");

    if (checkin && checkout.value) {
        const checkinDate = new Date(checkin);
        const checkoutDate = new Date(checkout.value);

        if (checkinDate >= checkoutDate) {
            let minCheckoutDate = new Date(checkinDate.getTime() + 86400000);
            checkout.value = minCheckoutDate.toISOString().split("T")[0];
        }
        checkout.min = checkin;

        const differenceDays = Math.ceil((new Date(checkout.value) - new Date(checkin)) / (1000 * 3600 * 24));
        let value = differenceDays * 49.70;

        let formattedValue = "R$ ";
        formattedValue += value.toLocaleString("pt-BR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
        });
        value = formattedValue;
        document.getElementById("reservationCost").value = value;
    }
}

function cpfFormatter(input) {
    let cpf = input.value.replace(/\D/g, '');
    cpf = cpf.replace(/^(\d{3})(\d)/g, '$1.$2');
    cpf = cpf.replace(/^(\d{3})\.(\d{3})(\d)/g, '$1.$2.$3');
    cpf = cpf.replace(/^(\d{3})\.(\d{3})\.(\d{3})(\d)/g, '$1.$2.$3-$4');
    input.value = cpf;
}

function showSuccess(event){
    const deleteForm = document.getElementById("deleteForm");
    deleteForm.addEventListener("click", showSuccess);

    event.preventDefault();

    const successMessage = document.getElementById("successMessage");
    successMessage.classList.add("show");

    setTimeout(function (){
        deleteForm.submit();
    }, 2000);
}
