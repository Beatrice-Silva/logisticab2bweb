
function validarCampos(){
        const email = document.getElementById("email");
        const senha = document.getElementById("senha");
        const botao = document.getElementById("botao");
                
        if(email.value.length > 0 && senha.value.length > 0){
            botao.disabled = false;
        }else{
                botao.disabled = true;
        }





    
}
