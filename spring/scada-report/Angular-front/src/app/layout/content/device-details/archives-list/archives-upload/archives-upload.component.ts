import {Component, Input, OnInit} from '@angular/core';
import {ShowArchivesOperationService} from '../../../../../service/showArchivesOperation/show-archives-operation.service';

@Component({
  selector: 'app-archives-upload',
  templateUrl: './archives-upload.component.html',
  styleUrls: ['./archives-upload.component.scss']
})
export class ArchivesUploadComponent implements OnInit {
  @Input() deviceNum;
  currentTab = 'upload';
  constructor(
    private showArchivesOperationService: ShowArchivesOperationService
  ) { }

  ngOnInit() {
  }
  showList() {
    this.showArchivesOperationService.showArchivesOperation.next(true);
  }
}
