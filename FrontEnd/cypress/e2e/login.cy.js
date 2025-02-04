describe('Login Feature', () => {
  beforeEach(() => {
    // Visit the login page before each test
    cy.visit('/login');
  });

  it('should successfully log in with valid credentials', () => {
    // Enter valid username and password
    cy.get('input[name="username"]').type('validUser');
    cy.get('input[name="password"]').type('validPassword');
    
    // Click the login button
    cy.get('button[type="submit"]').click();
    
    // Check if the URL is redirected to the dashboard
    cy.url().should('include', '/dashboard');
    
    // Check if the logout button is visible
    cy.get('button').contains('Logout').should('be.visible');
  });

  it('should fail to log in with invalid password', () => {
    // Enter valid username and invalid password
    cy.get('input[name="username"]').type('validUser');
    cy.get('input[name="password"]').type('invalidPassword');
    
    // Click the login button
    cy.get('button[type="submit"]').click();
    
    // Check if the error message is displayed
    cy.get('.error-message').should('be.visible').and('contain', 'Invalid username or password');
  });
});