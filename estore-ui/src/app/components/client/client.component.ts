import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/interfaces/user';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  user$: Observable<User | null> = this.usersService.user$;
  userBox: string = "";

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    const id: string | null = localStorage.getItem("user")
    if (id != null) {
      this.usersService.getUserById$(parseInt(id))
        .subscribe();
    }
  }

  userSearching(value: string): void {
    this.userBox = value;
  }
}
