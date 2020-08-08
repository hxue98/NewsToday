import React, { Fragment } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';

const Navbar = ({ icon, title }) => {
  //TODO authLink: display username

  const guestLink = (
    <Fragment>
      <li>
        <Link to='/login'>Login</Link>
      </li>

      <li>
        <Link to='/register'>Register</Link>
      </li>
    </Fragment>
  );

  return (
    <div className='navbar bg-primary'>
      <Link to='/'>
        <i className={icon} /> {title}
      </Link>
      <div>{guestLink}</div>
    </div>
  );
};

Navbar.prototype = {
  title: PropTypes.string.isRequired,
  icon: PropTypes.string,
};

Navbar.defaultProps = {
  title: 'News Today',
  icon: 'fas fa-id-card-alt',
};

export default Navbar;
