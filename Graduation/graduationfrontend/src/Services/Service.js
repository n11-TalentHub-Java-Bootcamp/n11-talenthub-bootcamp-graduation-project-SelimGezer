import axios from "axios";

class UsrService{

    gellAllUser(){

        const url = 'api/v1/users';

        return axios.get(url);
    }

    addUser(user){

        const url = 'api/v1/users';

        return axios.post(url,user);
    }

    getCredit(data){

        const url = 'api/v1/credits/?'+"identificationNumber="+data.identificationNumber+"&"+"birthday="+data.birthday.split('T')[0];

        return axios.get(url);
    }

    

}

export default new UsrService();