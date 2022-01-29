import React from "react"
import './Container.css';
import GetAllUser from "../Get/GetAllUser";
import AddUser from "../Post/AddUser";


class Container extends React.Component {
    render() {
        return (

            <div class="container">
       
                <main class="main"><GetAllUser></GetAllUser> </main>
                <main class="main"><AddUser></AddUser> </main>
            </div>

        );
    }

}


export default Container;