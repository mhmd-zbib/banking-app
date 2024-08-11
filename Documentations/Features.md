# User Class

## Properties
- **firstname**: First name of the user.
- **lastname**: Last name of the user.
- **username**: Username of the user.
- **address**: Address of the user.
- **phone**: Phone number of the user.
- **password**: Password for the user account.
- **email**: Email address of the user.
- **isEmailVerified**: Boolean indicating if the email is verified.
- **isAccountVerified**: Boolean indicating if the account is verified.
- **Wallet**: Associated wallet object.

## Methods
- **createAccount()**: Creates a new user account.
- **getInfo()**: Retrieves information about the user.
- **deleteAccount()**: Deletes the user account.
- **updateAccount()**: Updates user account information.

---

# Wallet Class

## Properties
- **User**: Associated user object.
- **balance**: Current balance in the wallet.
- **currency**: Currency type of the wallet.
- **WalletStatus()**: Status of the wallet.

## Methods
- **createWallet()**: Creates a new wallet.
- **deleteWallet()**: Deletes the wallet.
- **updateWallet()**: Updates wallet information.
- **getWalletInfo()**: Retrieves information about the wallet.
- **transactions()**: Manages and retrieves transaction history.
