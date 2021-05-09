$(document).ready(function () {
    $('#comment_add').click(function () {



        var nom =$('#comment_text').val().toUpperCase();


        var myarr = ["G1", "G2", "G3"];
        var array = [];
        var valid=0;
        var array1 = [];
        var valid1=0;
        array = nom.split(" ");

        var valid1=0;


        $.each( array, function( index, value ,e){
            if (myarr.indexOf(value) > -1)
            { valid=1;
                return false;


            }

        });



        if (nom.length==0)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ Comment',
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
        else if (nom.length >25)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ Comment > 25 caracters',
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

        else if (valid==1)
        {
            swal({
                title: 'Error!',
                text: 'eviter les gros mots dans le champ Comment',
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

        else if((nom.indexOf('G1') != -1 ) || (nom.indexOf('G2') != -1 )||(nom.indexOf('G3') != -1 ))
        {
            swal({
                title: 'Error!',
                text: 'eviter les gros mots dans le champ title',
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