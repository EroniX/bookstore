import {
  MatButtonModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatTableModule,
  MatTabsModule
} from '@angular/material';
import {MatChipsModule} from '@angular/material/chips';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';
import {NgModule} from "@angular/core";
import {CdkTableModule} from '@angular/cdk/table';

@NgModule({
  exports: [
    MatChipsModule,
    MatAutocompleteModule,
    MatTooltipModule,
    MatDialogModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule,
    MatTableModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatTabsModule,
    CdkTableModule,
    MatIconModule,
    MatDatepickerModule,
    MatRadioModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatPaginatorModule,
    MatSortModule,
    MatGridListModule]
})

export class MaterialModule {
}
