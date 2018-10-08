import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class SignUpForm extends Component {
    constructor() {
        super();

        this.state = {
            email: '',
            password: '',
            firstName: '',
            lastName: '',
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        let target = e.target;
        let value = target.value;
        let name = target.name;

        this.setState({
          [name]: value
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        fetch('https://localhost:8058/db-wrapper-service/addMemberData',{
          method: 'POST',
          headers: {
            'Accept':'application/json',
            'Content-Type':'application/json',
          },
          body: JSON.stringify({
            'firstName':this.state.firstName,
            'lastName':this.state.lastName,
            'email':this.state.email,
            'password':this.state.password
          })
        }).then(this.handleRedirect)

        console.log('The form was submitted with the following data:');
        console.log(this.state);

    }

    handleRedirect(res){
      if(res.status === 200)
      {
       window.location.href = 'http://localhost:3000/#/react-auth-ui/sign-in' 
      }
    }

    render() {
        return (

        <div className="FormCenter">
            <form onSubmit={this.handleSubmit} className="FormFields">

              
              <div className="FormField">
                <label className="FormField__Label" htmlFor="firstName">First Name</label>
                <input type="text" id="firstName" className="FormField__Input" placeholder="Enter your First name" name="firstName" value={this.state.firstName} onChange={this.handleChange} />
              </div>

              <div className="FormField">
                <label className="FormField__Label" htmlFor="lastName">Last Name</label>
                <input type="text" id="lastName" className="FormField__Input" placeholder="Enter your Last name" name="lastName" value={this.state.lastName} onChange={this.handleChange} />
              </div>

              <div className="FormField">
                <label className="FormField__Label" htmlFor="password">Password</label>
                <input type="password" id="password" className="FormField__Input" placeholder="Enter your password" name="password" value={this.state.password} onChange={this.handleChange} />
              </div>

              <div className="FormField">
                <label className="FormField__Label" htmlFor="email">E-Mail Address</label>
                <input type="email" id="email" className="FormField__Input" placeholder="Enter your email" name="email" value={this.state.email} onChange={this.handleChange} />
              </div>

              <div className="FormField">
                  <button className="FormField__Button mr-20">Sign Up</button> <Link to="/sign-in" className="FormField__Link">I'm already member</Link>
              </div>

            </form>
        </div>
        );
    }
}

export default SignUpForm;