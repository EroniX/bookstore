import {Component, Input} from '@angular/core';
import {MenuItem} from '../../../model/menu-item';

@Component({
  selector: 'side-menu-item',
  templateUrl: './side-menu-item.component.html',
  styleUrls: ['../../menu.css']
})
export class SideMenuItemComponent {

  @Input() menuItem: MenuItem;
}
