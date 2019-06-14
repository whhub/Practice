import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-algorithm-model',
  templateUrl: './algorithm-model.component.html',
  styleUrls: ['./algorithm-model.component.scss'],
  animations: [
    trigger('collapseMotion', [
      state('expanded', style({ height: '*', padding: '10px 0' })),
      // state('collapsed', style({ height: 0, overflow: 'hidden' })),
      // state('hidden', style({ height: 0, display: 'none' })),
      state('hidden', style({ height: 0, overflow: 'hidden', padding: 0 })),
      // transition('expanded => collapsed', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('expanded => hidden', animate(`100ms ease-out`)),
      // transition('collapsed => expanded', animate(`150ms cubic-bezier(0.645, 0.045, 0.355, 1)`)),
      transition('hidden => expanded', animate(`100ms ease-in`))
    ])
  ]
})
export class AlgorithmModelComponent implements OnInit {
  value;
  isShowTool = false;
  isVisible = false;
  constructor() { }

  ngOnInit() {
  }
  showModal() {
    this.isVisible = true;
  }
  handleOk(): void {
    console.log('Button ok clicked!');
    this.isVisible = false;
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }
}
