window.onload = function (ev) {
    var add = document.getElementById("add")
    add.onclick = function (ev1) {
        var add_window = open(contextPath + "/jsp/add.jsp");
        add_window.onclose = location.reload;
    }

    var select_all = document.getElementById("select_all");
    select_all.onchange = function (ev1) {
        var remove_boxs = document.getElementsByClassName("remove_box");
        for (var index in remove_boxs) {
            remove_boxs[index].checked = select_all.checked;
        }
    }

    var remove_select = document.getElementById("remove_select");
    remove_select.onclick = function (ev1) {
        var list = [];
        var remove_boxs = document.getElementsByClassName("remove_box");
        for (var index in remove_boxs) {
            // WTF???
            if (typeof (remove_boxs[index].getAttribute) === "function" && remove_boxs[index].checked === true) {
                list.push(remove_boxs[index].getAttribute("data-user-id"));
            }
        }
        if (confirm("您确定要执行批量删除吗？")) {
            $.post(contextPath + "/removeUser", {
                uid: list
            }, function (_) {
                location.reload();
            }, "text");
        }
    }
}

function update(uid) {
    var add_window = open(contextPath + "/updateUser?uid=" + uid);
    add_window.onclose = location.reload;
}

function remove(uid) {
    var list = [];
    list.push(uid)
    if (confirm("你确定要删除ID为" + uid + "的用户吗？")) {
        $.post(contextPath + "/removeUser", {
            uid: list
        }, function (_) {
            location.reload();
        }, "text");
    }
}

function select_checkbox() {
    var remove_boxs = document.getElementsByClassName("remove_box");
    for (var index in remove_boxs) {
        if (remove_boxs[index].checked === false) {
            document.getElementById("select_all").checked = false;
            return;
        }
    }
    document.getElementById("select_all").checked = true;
}

function get_query_parameter() {
    return {
        name: document.getElementById("exampleInputName2").value,
        birthplace: document.getElementById("exampleInputName3").value,
        email: document.getElementById("exampleInputEmail2").value
    }
}

function last_page(current_page, rows) {
    var par = get_query_parameter()
    location.href = contextPath + "/userlist?name=" + par.name +
        "&birthplace=" + par.birthplace + "&email=" + par.email +
        "&page=" + (+current_page - 1) + "&rows=" + rows;
}

function page(page_index, rows) {
    var par = get_query_parameter()
    location.href = contextPath + "/userlist?name=" + par.name +
        "&birthplace=" + par.birthplace + "&email=" + par.email +
        "&page=" + (+page_index) + "&rows=" + rows;
}

function next_page(current_page, rows) {
    var par = get_query_parameter()
    location.href = contextPath + "/userlist?name=" + par.name +
        "&birthplace=" + par.birthplace + "&email=" + par.email +
        "&page=" + (+current_page + 1) + "&rows=" + rows;
}

function fuzzy_query(rows) {
    var par = get_query_parameter()
    location.href = contextPath + "/userlist?name=" + par.name +
        "&birthplace=" + par.birthplace + "&email=" + par.email +
        "&page=1&rows=" + (+rows);
}










