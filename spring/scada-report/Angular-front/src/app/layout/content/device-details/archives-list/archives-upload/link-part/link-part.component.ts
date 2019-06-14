import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-link-part',
  templateUrl: './link-part.component.html',
  styleUrls: ['./link-part.component.scss']
})
export class LinkPartComponent implements OnInit {
  selectedValue;
  prefixName = '示例';
  suffixName = 'PPT';
  BeforeUpload = (file: File) => {
    console.log(file);
    const name = file.name;
   this. prefixName = name.split('.')[0];
   this. suffixName = name.split('.')[1];
  }
  constructor() { }

  ngOnInit() {
  }

}
