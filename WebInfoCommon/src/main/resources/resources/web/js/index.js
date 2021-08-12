function onload() {
    updatePageInfo();
}

function updatePageInfo() {
    let header = document.getElementById("header");
    let stat = document.getElementById("stat");
    header.innerText = "Идёт загрузка...";
    stat.innerHTML = "";
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/json/basic.json', true);
    xhr.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState !== 4) return;
        let data = JSON.parse(xhr.responseText);
        header.innerText = "Мод запущен на сервере";
        Tables.appendLine(stat, ["Версия игры", data.MineV]);
        Tables.appendLine(stat, ["Игроки", data.Players]);
    }
}

document.addEventListener("DOMContentLoaded", onload);
