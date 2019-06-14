import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-part',
  templateUrl: './upload-part.component.html',
  styleUrls: ['./upload-part.component.scss']
})
export class UploadPartComponent implements OnInit {
  value;
  fileList = [];
  currentType = 'ppt';
  constructor() { }

  ngOnInit() {
  }

}
