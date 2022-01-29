import React from "react";
import './Login.css';
import logo from '../Resources/logo.png';


class Login extends React.Component {
    

    render() {

        const mystyle = {
            color: "white",
            backgroundColor: "DodgerBlue",
            padding: "5px",
            fontFamily: "Arial"
          };

      console.log(React.version);
        return (
<>      
            <div className="wrapper">
                <div className="logo">
                    <img src={logo} alt="" /> </div>
                <div className="text-center mt-4 name">Hoşgeldiniz</div>
                <form className="p-3 mt-3">
                    <div className="form-field d-flex align-items-center"> 
                        <span className="far fa-user"></span>
                        <input type="text" name="admin" id="admin" placeholder="Admin"/> 
                    </div>
                    <div className="form-field d-flex align-items-center">
                        <span className="fas fa-key"></span>
                        <input type="password" name="password" id="pwd" placeholder="Password" /></div> 
                        <button type='button' className="btn mt-3" onClick={valid}>Login</button>
                        {/* <h5 id='errorText' style={ {textAlign:'center' ,padding:'10px'}}></h5> */}
                        <div id='errorText' style={ {textAlign:'center' ,padding:'10px'}}></div>
                </form>

               <div className="text-center mt-4 name" > Admin: admin</div>
               <div className="text-center mt-4 name" > Şifre: 123456789</div>

            </div>
</>

        );



        function valid() {
      
            var adminText=document.getElementById("admin").value;  
            var pswText=document.getElementById("pwd").value;  
        
             console.log("admin:"+adminText);
             console.log(pswText);
        
            if(adminText=="admin" && pswText==123){
                console.log("başarılı");
                //  <HomePage></HomePage>
                // <Navigate to='/gec'></Navigate>
            }else{
                document.getElementById("errorText").innerHTML="Hatalı giriş yaptınız!";
                setTimeout(refresh, 1500);
            }
        }
        
        function refresh(){
            document.getElementById("errorText").innerHTML="";
        }
        


    }
 
}

export default Login;