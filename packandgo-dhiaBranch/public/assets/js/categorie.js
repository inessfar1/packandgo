$(document).ready(function () {
    $('#categorie_save').click(function () {



        var nom =$('#categorie_Title').val().toUpperCase();

        var desc =$('#categorie_description').val().toUpperCase();
        var myarr = ["G1", "G2", "G3"];
        var array = [];
        var valid=0;
        var array1 = [];
        var valid1=0;
        array = nom.split(" ");
        array1 = desc.split(" ");
        var valid1=0;


        $.each( array, function( index, value ,e){
            if (myarr.indexOf(value) > -1)
            { valid=1;
                return false;


            }

        });
        $.each( array1, function( index, value ,e){
            if (myarr.indexOf(value) > -1)
            { valid1=1;
                return false;


            }

        });


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
        else if (nom.length >25)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ Title > 25 caracters',
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
        else if (desc.length >255)
        {
            swal({
                title: 'Error!',
                text: 'remplir champ description > 255 caracters',
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
        else if (valid==1)
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
        else if (valid1==1)
        {
            swal({
                title: 'Error!',
                text: 'eviter les gros mots dans le champ description',
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
        else if((desc.indexOf('G1') != -1 ) || (desc.indexOf('G2') != -1 )||(desc.indexOf('G3') != -1 ))
        {
            swal({
                title: 'Error!',
                text: 'eviter les gros mots dans le champ description',
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