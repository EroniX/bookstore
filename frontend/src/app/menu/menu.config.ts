import {MenuItemVisibility} from './menu-item.enum';
import {MenuItem} from './model/menu-item';
import {Menu} from './model/menu';
import {MenuItemGroup} from './model/menu-item-group';
import {MenuItemBase} from './model/menu-item-base';

export class MenuConfig {

  private static mainMenu = [
    new MenuItem('books', 'Könyvek listája', MenuItemVisibility.OnlyAuthenticated, 'BOOK_LIST'),
    new MenuItem('books/create', 'Könyv létrehozása', MenuItemVisibility.OnlyAuthenticated, 'BOOK_LIST'),
  ];

  private static rightMenu = [
    new MenuItem('logout', 'Kijelentkezés', MenuItemVisibility.OnlyAuthenticated, null),
    new MenuItem('login', 'Bejelentkezés', MenuItemVisibility.OnlyUnauthenticated, null)
  ];

  static createMainMenu(authenticated: boolean, permissions: Array<string>): Menu {
    return new Menu(MenuConfig.getFiltered(MenuConfig.mainMenu, authenticated, permissions));
  }

  static createRightMenu(authenticated: boolean, permissions: Array<string>): Menu {
    return new Menu(MenuConfig.getFiltered(MenuConfig.rightMenu, authenticated, permissions));
  }

  private static getFiltered(menuItems: Array<MenuItemBase>,
                             authenticated: boolean,
                             permissions: Array<string>): Array<MenuItemBase> {

    return menuItems
      .map(menuItemBase => {
        if (menuItemBase instanceof MenuItemGroup) {
          const menuItemGroup = menuItemBase as MenuItemGroup;

          const newMenuItemGroup = new MenuItemGroup(
            this.getFiltered(menuItemGroup.children, authenticated, permissions),
            menuItemGroup.text);

          if (newMenuItemGroup.children.length !== 0) {
            return newMenuItemGroup;
          }
        } else {
          const menuItem = menuItemBase as MenuItem;

          if (((menuItem.visibility.valueOf() === MenuItemVisibility.All.valueOf())
            || (authenticated && menuItem.visibility.valueOf() === MenuItemVisibility.OnlyAuthenticated.valueOf())
            || (!authenticated && menuItem.visibility.valueOf() === MenuItemVisibility.OnlyUnauthenticated.valueOf()))
            && (menuItem.permission == null || permissions.includes(menuItem.permission))) {
            return menuItem;
          }
        }
      })
      .filter(menuItemBase => menuItemBase != null);
  }
}
