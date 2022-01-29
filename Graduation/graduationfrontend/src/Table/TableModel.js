import React from 'react';
import './TableModel.css';

class TableModel extends React.Component {


    constructor(props) {
        super(props);
    }

    render() {
        return (
            <>
                <tr>

                    <th scope="row">{this.props.usr.id}</th>

                    <td>{this.props.usr.fullname}</td>
                    <td>{this.props.usr.identificationNumber}</td>
                    <td>{this.props.usr.monthlyIncome}</td>
                    <td>{this.props.usr.phoneNumber}</td>
                    <td>{this.props.usr.birthday}</td>
                    <td>{this.props.usr.guaranteePrice}</td>
                    {/* <td>
                        <div class="btn-group" role="group" aria-label="Basic mixed styles example" >
                            <button type="button" class="btn btn-danger">Sil</button>
                            <button type="button" class="btn btn-warning">Güncelle</button>
                        </div>
                    </td> */}

                </tr>

            </>
        );
    }


}

export default TableModel;