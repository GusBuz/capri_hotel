function changeTab(tabIndex){
    const tableContent = document.getElementsByClassName("tableContent");
    for (let i = 0; i < tableContent.length; i++) {
        tableContent[i].classList.remove("show");
    }
    const selectedTable = document.getElementById("content" + tabIndex);
    selectedTable.classList.add("show");

    const reservationTab = document.getElementById("reservationTab")
    const guestTab = document.getElementById("guestTab")

    if (tabIndex === 1) {
        reservationTab.classList.add("selected");
        guestTab.classList.remove("selected");
    } else if (tabIndex === 2) {
        reservationTab.classList.remove("selected");
        guestTab.classList.add("selected");
    }
} changeTab(1);

function updateSearchInput(){
    const filterType = document.getElementById("filterType");
    const searchInput = document.getElementById("searchInput");
    const dateSearchInput = document.getElementById("dateSearchInput");

    if (filterType.value === "name"){
        searchInput.style.display = "inline-block";
        dateSearchInput.style.display = "none";
        searchInput.value = "";
        searchInput.oninput = function (){
            nameFormatter(searchInput);
        }
    } else if (filterType.value === "cpf") {
        searchInput.style.display = "inline-block";
        searchInput.maxLength = 14;
        dateSearchInput.style.display = "none";
        searchInput.value = "";
        searchInput.oninput = function (){
            cpfFormatter(searchInput);
        }
    } else if(filterType.value === "checkinDate"){
        searchInput.style.display = "none";
        dateSearchInput.style.display = "inline-block";
    }
} updateSearchInput();

function validateRadio(formId){
    const radios = document.querySelectorAll(`#${formId} input[type="radio"][name="registry"]`);
    let isAnySelected = false;

    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            isAnySelected = true;
            break;
        }
    }

    if (!isAnySelected) {
        alert("Nenhum registro selecionado, selecione um registro para continuar.");
        return false;
    }
    return true;
}