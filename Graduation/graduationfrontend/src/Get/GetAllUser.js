import React from 'react';
import Service from '../Services/Service';
import TableModel from '../Table/TableModel.js';


class GetAllUser extends React.Component {

  constructor() {
    super();

    this.state = {
      usrList: []
    }
  }


  componentDidMount() {

    Service.gellAllUser()
      .then(responce => this.handleResponce(responce))
      .catch(error => this.handleError(error));

  }

  handleResponce(response) {
    console.log(response);
    this.setState(
      { usrList: response.data }
    )
  }

  handleError(error) {
    console.log(error)
  }

  render() {
    return (
      <div>

        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Fullname</th>
              <th scope="col">Identification Number</th>
              <th scope="col">Monthly Income</th>
              <th scope="col">Phone Number</th>
              <th scope="col">Birthday</th>
              <th scope="col">GuaranteePrice</th>
            </tr>
          </thead>
          <tbody>
          {this.state.usrList.map(usr =>
             
            //  <div key={usr.id}>{usr.fullname}</div>
            <TableModel key={usr.id} usr={usr}></TableModel>
             
             )}
            {/* <TableModel key={usr.id} name={usr.fullname}></TableModel>
            <TableModel></TableModel>
            <TableModel></TableModel> */}
          </tbody>
        </table>
      </div>





    );
  }
}

export default GetAllUser;