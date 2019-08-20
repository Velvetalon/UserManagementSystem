window.onload = function (ev) {
    var back_button = document.getElementById("back");
    back_button.onclick = function (ev1) {
        window.close();
    }

    var submit_button = document.getElementById("submit");
    submit_button.onclick = function (ev1) {
        if (
            document.getElementById("name").value.length > 0  &&
            document.getElementById("age").value.length > 0 &&
            document.getElementById("qq_number").value.length > 0 &&
            document.getElementById("email").value.length > 0
        ) {
            var gender;
            var genders = document.getElementsByClassName("gender")
            for(var i in genders){
                if(genders[i].checked === true){
                    gender = genders[i].value;
                }
            }

            $.post("/wop/addUser", {
                name: document.getElementById("name").value,
                gender: gender,
                age: document.getElementById("age").value,
                birthplace: $('#birthplace option:selected').val(),
                qq_number: document.getElementById("qq_number").value,
                email: document.getElementById("email").value
            }, function (data) {     //新增完毕后关闭页面
                alert("添加完毕，按确定键关闭页面")
                window.close();
            },"text");
            //禁用提交按钮防止反复提交
            submit_button.disabled = "disabled";
        }else{
            alert("提交失败，所有数据都必须输入！")
        }
    }
}