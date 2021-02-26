class Tables {
    static appendLine(table,array) {
        let tr = document.createElement("tr");
        for (let arrayKey in array) {
            let td = document.createElement("td");
            td.innerText = array[arrayKey];
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
}
