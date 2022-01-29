import React from "react";
import './AddUser.css';
import serialize from 'form-serialize';
import Service from "../Services/Service";
import Result from "./Result";

class AddUser extends React.Component {

    constructor() {
        super();
        this.state = { show: false, creditResult: "", creditLimit: 12 };
    }

    clearForm() {
        document.getElementById("product-form").reset();
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        const newUser = serialize(e.target, { hash: true })
        //console.log(newUser);
        Service.addUser(newUser)
            .then(responce => this.handleResponce(responce))
            .catch(error => this.handleError(error))


    }

    handleResponce(response) {

        Service.getCredit(response.data)
            .then(responce2 => this.handleResponceGetCredit(responce2))
            .catch(error2 => this.handleErrorGetCredit(error2))

    }

    handleError(error) {
        let message = typeof error.response !== "undefined" ? error.response.data.message : error.message;
        console.warn("error", message);
    }


    handleResponceGetCredit(response) {
        console.log(response)

        var credit = response.data.credit;
        this.setState({ show: !this.state.show, creditLimit: credit.creditLimit, creditResult: credit.creditResult })
    }

    handleErrorGetCredit(error) {
        let message = typeof error.response !== "undefined" ? error.response.data.message : error.message;
        console.warn("error", message);
    }


    render() {
        return (
            <>
                <div style={{ backgroundColor: "#FFFFFF" }}>
                    <h1 style={{ textAlign: "center" }}>Yeni Kullanıcı Ekle</h1>
                    <form id="product-form" className="mt-5" onSubmit={this.handleFormSubmit} >

                        <div className="birimItem" >
                            <span className="input-group-text" id="addon-wrapping">Ad-Soyad</span>
                            <input type="text" name="fullname" required className="form-control" minlength="7" title="Minumum 7 karakterli bir giriş yapınız!" />
                        </div>

                        <div className="birimItem">
                            <span className="input-group-text" id="addon-wrapping">Kimlik Numarası</span>
                            <input type="tel" name="identificationNumber" pattern="[1-9]{1}[0-9]{10}" title="Lütfen geçerli bir kimlik numrası giriniz!" required className="form-control" aria-label="Username" aria-describedby="addon-wrapping" />
                        </div>

                        <div className="birimItem">
                            <span className="input-group-text" id="addon-wrapping">Aylık Kazanç</span>
                            <input type="number" name="monthlyIncome" required className="form-control" aria-label="Username" aria-describedby="addon-wrapping" />
                        </div>

                        <div className="birimItem">
                            <span className="input-group-text" id="addon-wrapping">Telefon Numarası</span>
                            <input type="tel" name="phoneNumber" pattern="[+]{1}[0-9]{12}" title="Lütfen alan koduyla birlikte giriş gerçekleştiriniz!" required className="form-control" aria-label="Username" aria-describedby="addon-wrapping" />
                        </div>

                        <div className="birimItem">
                            <span className="input-group-text" id="addon-wrapping">Doğum Tarihi</span>
                            <input type="date" name="birthday" required className="form-control" aria-label="Username" aria-describedby="addon-wrapping" />
                        </div>

                        <div className="birimItem">
                            <span className="input-group-text" id="addon-wrapping">Teminat Bedeli</span>
                            <input type="number" name="guaranteePrice" className="form-control" aria-label="Username" aria-describedby="addon-wrapping" />
                        </div>

                        <input type="reset" className="btn btn-warning btn-block" onClick={this.clearForm} value="Temizle" style={{ marginTop: "20px" }} />
                        <input type="submit" className="btn btn-danger btn-block" value="Kaydet" />


                        <Result show={this.state.show} creditLimit={this.state.creditLimit} creditResult={this.state.creditResult}> hide={false}</Result>
                    </form>
                </div>
            </>
        );
    }
}

export default AddUser;