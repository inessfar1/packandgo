$(document).ready(function () {
    $('#blog_save').click(function () {



        var nom =$('#blog_title').val();
        var desc =$('#blog_description').val();

        var img =$('#blog_image').val();


        if (nom.length==0)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ Title',
                icon: 'error',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            });
            return false;

        }
        else if (desc.length==0)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ Description',
                icon: 'error',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            });
            return false;

        }
        else if (img.length==0)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ image',
                icon: 'error',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            });
            return false;

        }


    });


});