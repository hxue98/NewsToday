import React, { Fragment } from 'react';

import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav>
      <div className='nav-wrapper'>
        <Link to='/' className='brand-logo'>
          <i className='material-icons'>cloud</i>Logo
        </Link>
        <ul className='right hide-on-med-and-down'>
          <li>
            <Link to='/Search'>
              <i className='material-icons'>search</i>
            </Link>
          </li>
          <li>
            <a href='badges.html'>
              <i className='material-icons'>view_module</i>
            </a>
          </li>

          <li>
            <a class='dropdown-trigger' data-target='dropdown1'>
              <i className='material-icons'>more_vert</i>
            </a>
            <ul id='dropdown1' class='dropdown-content'>
              <li>
                <Link to='/register'>Register</Link>
              </li>
              <li>
                <Link to='/login'>Login</Link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
