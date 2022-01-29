import React from "react";
import { Modal, Button } from "react-bootstrap"



class Result extends React.Component {


  constructor(props) {

    super(props);

    this.state = {
      show: false
    };


    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);

  }

  start() {
    console.log("start")
    this.showModal();
  }

  showModal = () => {
    console.log("show")
    this.setState({ show: true });
  };


  hideModal = () => {
    this.setState({ show: false });
  };


  componentDidUpdate(prev) {
    // console.log("çalıştı")
    // console.log("props:" + this.props.show)
    // console.log("state:" + this.state.show)

    if (this.props.show !== prev.show) {
      this.showModal();
      // console.log("girdi")
    }
  }

  render() {



    return (
      <>
        {/* <Button variant="primary" onClick={this.showModal}>
            Launch static backdrop modal
          </Button>
     */}
        <Modal
          show={this.state.show}
          onHide={this.hideModal}
          backdrop="static"
          keyboard={false}
        >
          <Modal.Header closeButton>
            <Modal.Title>Kredi Sonucu</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            Kredi Limiti : {this.props.creditLimit} ve Sonuç : {this.props.creditResult}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="danger" onClick={this.hideModal}>
              Kapat
            </Button>

          </Modal.Footer>
        </Modal>
      </>
    );
  }


}

export default Result;