export class Routes {
  static LOGIN: String = 'auth/login';
  static LOGOUT: String = 'user/logout';
  static BOOKS: String = 'books';
}

export class Server {
  private static address: String = 'localhost';
  private static port: String = '8080';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`;
  }
}


