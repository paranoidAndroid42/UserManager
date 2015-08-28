

$(document).ready(function () {

    $('#add_user').click(function () {

        $("#box").dialog();
        $("#phone").mask("(999) 999-9999");

    });

    $('#add_user_form').submit(function()
    {
        $.ajax({
            type: 'POST',
            url: 'AddUserServlet',
            data: $('#add_user_form').serialize(),
            success: function () {
                //animasyon
            }
        })
    });

    $('#edit_user_form').submit(function()
    {
        $.ajax({
            type: 'POST',
            url: 'UpdateUserServlet',
            data: $('#edit_user_form').serialize(),
            success: function () {
                //animasyon
            }
        })
    });

});

function deleteUser(id)
{
    $.ajax({
        type: 'POST',
        url: 'DeleteUserServlet',
        data: "id="+id,
        success: function () {
            //animasyon
        }
    })
}

function editUser(id)
{
    $("#editBox").dialog();
    $("#editPhone").mask("(999) 999-9999");

    $.ajax({
        type: 'POST',
        url: 'EditUserServlet',
        data: "id="+id,
        success: function () {
            //animasyon
        }
    })
}


